using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Text;

namespace Bbq.Data.DataConnectie
{
    public class DataConnection
    {
        private readonly MongoClient _client;

        public DataConnection(string connectionUrl)
        {
            _client = new MongoClient(connectionUrl);
        }

        public IMongoDatabase GetDatabase(string database)
        {
            return _client.GetDatabase(database);
        }
    }
}
