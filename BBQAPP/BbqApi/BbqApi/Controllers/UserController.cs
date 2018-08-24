using Bbq.Data.Domain;
using Bbq.Data.Domain.Dto;
using Bbq.Data.Interface;
using Microsoft.AspNetCore.Mvc;

namespace BbqApi.Controllers
{
    [Produces("application/json")]
    [Route("api/User")]
    public class UserController : Controller
    {
        public IUserRepository _userRepository { get; set; }

        public UserController(IUserRepository repository)
        {
            _userRepository = repository;
        }
        // GET: api/User
        [HttpGet]
        public IActionResult Get()
        {
           var userList = _userRepository.GetAllUsersFromDatabase();
            if(userList != null)
            {
                return Ok(userList);
            }
            return Ok("Geen users gevonden");
        }

        //GET: api/getUser
        [HttpPost("getUser")]
        public IActionResult GetGebruiker([FromBody] UserDto user)
        {
           var receivedUser =  _userRepository.GetUserFromDatabase(user);
            if (receivedUser == null)  return Ok("Geen gebruiker gevonden met deze gegevens");
            return Ok(receivedUser);
        }
        
        // POST: api/User
        [HttpPost]
        public IActionResult Post([FromBody]User user)
        {
            var createdUser = _userRepository.PostUserToDatabase(user);
            if(createdUser != null)
            {
                return Ok(createdUser);
            }
            return NotFound();
        }
        
        // PUT: api/User/5
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
