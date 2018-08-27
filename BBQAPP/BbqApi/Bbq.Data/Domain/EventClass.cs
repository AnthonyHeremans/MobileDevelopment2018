using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;
using System.Text;

namespace Bbq.Data.Domain
{
    public class EventClass
    {
        [BsonId]
        public ObjectId Id { get; set; }
        [BsonElement("username")]
        public string Username { get; set; }
        [BsonElement("eventName")]
        public string eventName { get; set; }
        [BsonElement("adress")]
        public string Adress { get; set; }
        [BsonElement("houseNumber")]
        public string houseNumber { get; set; }
        [BsonElement("city")]
        public string city { get; set; }
        [BsonElement("postalCode")]
        public string postalCode { get; set; }
        [BsonElement("numberOfMaxAttendees")]
        public string numberOfMaxAttendees { get; set; }
        [BsonElement("date")]
        public string date { get; set; }
        [BsonElement("time")]
        public string time { get; set; }
        [BsonElement("comment")]
        public string comment { get; set; }
        [BsonElement("filters")]
        public string filters { get; set; }
        [BsonElement("numberOfAttendees")]
        public string numberOfAttendees { get; set; }

    }
}
