using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Bbq.Data.Domain;
using Bbq.Data.Interface;
using Bbq.Data.Repository;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Cors.Internal;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;

namespace BbqApi
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddCors(o => o.AddPolicy("bbqPolicy", builder => { builder.AllowAnyOrigin().AllowAnyMethod().AllowAnyHeader(); }));
            services.Configure<MvcOptions>(options =>
            {
                options.Filters.Add(new CorsAuthorizationFilterFactory("bbqPolicy"));
            });
            // Services
            services.AddScoped<IUserRepository, UserRepository>();
            services.AddScoped<IEventRepository, EventRepository>();
            services.AddMvc();

        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();

            }
            app.UseCors("bbqPolicy");
            app.UseMvc();
        }
    }


}
