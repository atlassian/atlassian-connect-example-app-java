package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.sample.connect.app.atlassianconnectsampleappjava.models.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;


@RestController
public class Home {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public ModelAndView home(ModelMap model) {
        model.put("index", "Home Page");
        model.put("body", "You in the home page!");

        return new ModelAndView("home", model);
    }

    @GetMapping("/checkdb")
    public ModelAndView db(ModelMap model) {
        model.put("index", "Home Page");
        model.put("body", "You in the db page!");

        Long identifier = new Timestamp(System.currentTimeMillis()).getTime();

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Tenant(id INT, host varchar(100), sharedSecret varchar(100), clientKey varchar(500))");

        jdbcTemplate.execute("INSERT INTO Tenant VALUES (" + identifier + ", 'https://mytenant" + identifier + ".com', 'secret-" + identifier + "', 'clientKey-" + identifier + "')");

        List<Tenant> tenants = jdbcTemplate.query("SELECT * FROM Tenant", (rs, rowNum) -> {
            Tenant foundTenant = new Tenant();
            foundTenant.setId(rs.getInt("id"));
            foundTenant.setHost(rs.getString("host"));
            foundTenant.setSharedSecret(rs.getString("sharedSecret"));
            foundTenant.setClientKey(rs.getString("clientKey"));
            return foundTenant;
        });

        tenants.forEach(tenant -> {
            System.out.println(tenant.getId() + "==========" + tenant.getHost());
        });

        return new ModelAndView("home", model);
    }
}
