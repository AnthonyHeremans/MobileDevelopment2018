using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Bbq.Data.Domain;
using Bbq.Data.Domain.Dto;
using Bbq.Data.Interface;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace BbqApi.Controllers
{
    [Produces("application/json")]
    [Route("api/EventClass")]
    public class EventController : Controller
    {
        IEventRepository _eventRepository { get; set; }

        public EventController(IEventRepository repository)
        {
            _eventRepository = repository;
        }    
        // GET: api/EventClass
        [HttpGet]
        public IActionResult Get()
        {
            var eventList = _eventRepository.GetAllEventFromDatabase();
            if (eventList != null)
            {
                return Ok(eventList);
            }
            return Ok("Geen Events gevonden");
        }
        // POST: api/EventClass/createEvent
        [HttpPost("createEvent")]
        public ActionResult PostNewEvent([FromBody]EventClass value)
        {
            var createEvent = _eventRepository.PostUserToDatabase(value);
            if (createEvent != null)
            {
                return Ok(createEvent);
            }
            return Ok("Event is niet toegevoegd");        
        }

        // POST: api/EventClass/getEventByPerson
        [HttpPost("getEventByPerson")]
        public ActionResult getEventByUser([FromBody]String value)
        {
            var eventList = _eventRepository.GetEventByUser(value);
            if (eventList != null)
            {
                return Ok(eventList);
            }
            return Ok("Er zijn geen eventen gevonden voor deze persoon");
        }
        // PUT: api/EventClass/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody]string value)
        {
        }
        
        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
