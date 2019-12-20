package com.example.BusReservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BusReservation.models.Bus_Details;
import com.example.BusReservation.models.City;
import com.example.BusReservation.models.Timings;
import com.example.BusReservation.repositories.Bus_DetailsRepository;
import com.example.BusReservation.repositories.CityRepository;
import com.example.BusReservation.repositories.TimingsRepository;
import com.mysql.cj.Session;

import java.util.*;

@Controller
public class AdminController {
	@Autowired
	Bus_DetailsRepository bus_Details;
	@Autowired
	CityRepository city_name;
	@Autowired
	TimingsRepository timings;
	
	@RequestMapping(path = "/admin")
	public String adminHome(HttpServletRequest request,HttpServletResponse responce)
	{
			List list=bus_Details.findBusIds();
			List bus_detailsall=bus_Details.findAllBusDetails();
			List <Bus_Details>bus_detailsall1=bus_Details.findAllBusDetails();
			//Integer id=(Integer) list.get(0);
			//System.out.println("id"+id);
			Map<Integer,List<String>> city_lists=new <Integer,List<String>>HashMap();
			for(Bus_Details o:bus_detailsall1) {
				List<String> city=new ArrayList();
				if(o.getCity_List()!=null) {
				String ids[]=o.getCity_List().split(",");
				for(String id:ids) {
					city.add(city_name.findCityName(Integer.parseInt(id)));
				}
				
				}
				city_lists.put(o.getBus_id(),city);
			
			}
			request.setAttribute("city_lists",city_lists);
			request.setAttribute("bus_ids_list",list);
			request.setAttribute("bus_deatilsall",bus_detailsall);
			return "adimhome";
	}
	@RequestMapping(path="/addbus_details",method = RequestMethod.POST)
	public String addbus(HttpServletRequest request,ModelMap m)
	{
		String name=request.getParameter("bus_name");
		String weeks[]=request.getParameterValues("weeks");
		//System.out.println(name);
		String frequency="";
		for(int i=0;i<weeks.length;i++) {
			if(i!=(weeks.length-1)) {
				frequency+=weeks[i]+",";
			}
			else {
				frequency+=weeks[i];
			}
			
		}
		//System.out.println(frequency);
		
		String city_list="";
		int length=Integer.parseInt(request.getParameter("length"));
		String[] city=new String[length];
		String[] arrival_time=new String[length];
		String[] departure_time=new String[length];
		for(int i=1;i<=length;i++) {
			String cityid="";
			//List<String> allcitys=city_name.allCities();
			
			city[i-1]=request.getParameter("name"+i);
			if(city_name.findId(city[i-1])==null) {
				System.out.println(city[i-1]);
				City c=new City();
				c.setCity_name(city[i-1]);
				city_name.save(c);
				//System.out.println(city_name.findId(city[i-1]));
			}
			if(i==city.length) {
				city_list+=city_name.findId(city[i-1]);
			}
			else {
				city_list+=city_name.findId(city[i-1])+",";
			}
			//arrival_time[i-1]=request.getParameter("arri"+i);
			//departure_time[i-1]=request.getParameter("desi"+i);
		} 
		Bus_Details bs=new Bus_Details();
		bs.setBus_Name(name);
		bs.setFrequency(frequency);
		bs.setCity_List(city_list);
		bus_Details.save(bs);
		for(int i=1;i<=length;i++) {
			String arrival_time1=request.getParameter("arri"+i);
			String departure_time1=request.getParameter("desi"+i);
			Timings t=new Timings();
			t.setArrival_time(arrival_time1);
			t.setDeparture_time(departure_time1);
			t.setBus_Det(bs);
			timings.save(t);
		}
		
		
		//request.setAttribute("b_id",bs.getBus_id());
		
		return "redirect:/admin";
		
	}
	@RequestMapping(path="/updatebus_detailes")
	public String updatedetails(HttpServletRequest request) {
		Integer b_id=Integer.parseInt(request.getParameter("bus_id"));
		/*Bus_Details bs=new Bus_Details();
		bs.setBus_id(b_id);*/
		Optional<Bus_Details> d=bus_Details.findById(b_id);
		Bus_Details details=d.get();
		String cityids[]=details.getCity_List().split(",");
		List<String> citylist=new ArrayList();
		for(String id:cityids) {
			citylist.add(city_name.findCityName(Integer.parseInt(id)));
		}
		List<Timings> timingsList=details.getList();
		request.setAttribute("citylist", citylist);
		request.setAttribute("timingslist", timingsList);
		request.setAttribute("Bus_details", details);
		return "updatebusdetails";
		
	}
	@RequestMapping(path ="/delete")
		public String delete(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("bus_id"));
		Bus_Details bs=new Bus_Details();
		bs.setBus_id(id);
		List<Timings> tlist=timings.findtimings(bs);
		timings.deleteAll(tlist);
		
		System.out.println("delete mappting");
		bus_Details.delete(bs);
		
		return "redirect:/admin";
		}
	@RequestMapping(path="/buscards")
	public String buscard() {
		return "buscards";
	}
	
	/*
	 * @RequestMapping(path="/insertd") public String inserted() {
	 * 
	 * return "insertd"; }
	 */
	 @RequestMapping(path="/bus_detailsupdated")
	 public String  updated(HttpServletRequest request) 
	 {
		int bus_id=Integer.parseInt(request.getParameter("bus_id"));
		String bus_name=request.getParameter("bus_name");
		String bus_fre[]=request.getParameterValues("weeks");
		String frequny="";
		for(int i=0;i<bus_fre.length;i++) {
			if(i!=(bus_fre.length-1)) {
				frequny+=bus_fre[i]+",";
				
			}
			else {
				frequny+=bus_fre[i];
			}
		}
		//String citylist[]=request.getParameterValues("city_name");
		//String arrai[]=request.getParameterValues("arrai");
		//String desi[]=request.getParameterValues("desi");
		System.out.println(bus_id);
		System.out.println(bus_name);
		System.out.println(bus_fre);
		Bus_Details bs=new Bus_Details();
	
		 return "insertd";
	 }
	
	 @RequestMapping(path="/adminlogin")
		public String adminlogin() {
			return "adminlogin";
		}
	
}
