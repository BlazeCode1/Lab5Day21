package org.example.eventsystem.Controller;

import org.example.eventsystem.API.ApiResponse;
import org.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents(){
        return events;
    }


    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event){
        for (Event e : events){
            if(event.getID().equals(e.getID())){
                return new ApiResponse("Event With Given ID Already Added");
            }
        }
        events.add(event);
        return new ApiResponse("Event Added Successfully");
    }

    @PutMapping("/update")
    public ApiResponse updateEvent(@RequestBody Event event){

        for (Event e: events){
            if(e.getID().equals(event.getID())){
                events.set(events.indexOf(e), event);
                return new ApiResponse("Event Updated");
            }
        }
        return new ApiResponse("Event With Given ID not Found");
    }

    @DeleteMapping("/delete/{ID}")
    public ApiResponse deleteEvent(@PathVariable String ID){
        for (Event e: events){
            if(e.getID().equals(ID)){
                events.remove(e);
                return new ApiResponse("Event Removed!");
            }
        }
        return new ApiResponse("Event Not Found With Given ID!");
    }


    @PutMapping("/capacity/{ID}/{capacity}")
        public ApiResponse changeCapacity(@PathVariable String ID,@PathVariable int capacity) {

            for (Event e : events) {
                if (e.getID().equals(ID)) {
                    e.setCapacity(capacity);
                    return new ApiResponse("Capacity Changed For Event : " + e.getDescription());
                }

            }
            return new ApiResponse("Event Not Found.");
    }


    @GetMapping("/search/{ID}")
    public Object searchByID(@PathVariable String ID){
        for (Event e: events ){
            if(e.getID().equals(ID)){
                return e;
            }
        }
        return new ApiResponse("Event Not Found With Given ID");
    }
}
