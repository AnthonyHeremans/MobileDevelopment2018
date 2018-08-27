using Bbq.Data.DataConnectie;
using Bbq.Data.Domain;
using Bbq.Data.Interface;
using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Text;

namespace Bbq.Data.Repository
{
    public class EventRepository : IEventRepository
    {
        public IMongoDatabase Database { get; set; }
        public DataConnection Connection { get; set; }
        public EventRepository()
        {
            Connection = new DataConnection("mongodb://localhost:27017");
            Database = Connection.GetDatabase("bbqDatabase");
        }

        public EventClass PostUserToDatabase(EventClass eventClass)
        {
            eventClass.Id = ObjectId.GenerateNewId();
            Database.GetCollection<EventClass>("EventClass").InsertOne(eventClass);
            return eventClass;
        }
        public List<EventClass> GetEventByUser(String value)
        {
            // eventClass.Id = ObjectId.GenerateNewId();
            var eventListByUser = Database.GetCollection<EventClass>("EventClass").Find(e => e.Username == value).ToList();
            //Database.GetCollection<EventClass>("EventClass").InsertOne(eventClass);
            return eventListByUser;
        }
        public List<EventClass> GetAllEventFromDatabase()
        {
            return Database.GetCollection<EventClass>("EventClass").Find(_ => true).ToList();
        }
    }
}
