using Bbq.Data.DataConnectie;
using Bbq.Data.Domain;
using Bbq.Data.Domain.Dto;
using Bbq.Data.Interface;
using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Text;

namespace Bbq.Data.Repository
{
    public class UserRepository : IUserRepository
    {
        public IMongoDatabase Database { get; set; }
        public DataConnection Connection { get; set; }
        public UserRepository()
        {
            Connection = new DataConnection("mongodb://localhost:27017");
            Database = Connection.GetDatabase("bbqDatabase");
        }

        public User GetUserFromDatabase(UserDto user)
        {
            var result = Database.GetCollection<User>("User").Find(u => u.Username.Equals(user.Username) && u.Password.Equals(user.Password));
            if(result.Any())
            {
                return result.First();
            }
            return null;
        }

        public User PostUserToDatabase(User user)
        {
            user.Id = ObjectId.GenerateNewId();
            Database.GetCollection<User>("User").InsertOne(user);
            return user;
        }

        public List<User> GetAllUsersFromDatabase()
        {
            return Database.GetCollection<User>("User").Find(_ => true).ToList();
        }

        private void SeedDatabase()
        {

        }
    }
}
