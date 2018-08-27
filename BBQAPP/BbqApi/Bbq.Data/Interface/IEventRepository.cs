using Bbq.Data.Domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace Bbq.Data.Interface
{
    public interface IEventRepository
    {
        EventClass PostUserToDatabase(EventClass eventClass);
        List<EventClass> GetEventByUser(String value);
        List<EventClass> GetAllEventFromDatabase();
    }
}
