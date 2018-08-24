using Bbq.Data.Domain;
using Bbq.Data.Domain.Dto;
using System;
using System.Collections.Generic;
using System.Text;

namespace Bbq.Data.Interface
{
    public interface IUserRepository
    {
        User GetUserFromDatabase(UserDto user);
        User PostUserToDatabase(User user);
        List<User> GetAllUsersFromDatabase();
    }
}
