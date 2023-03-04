package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{

        if(countryName.equals("IND")||
        countryName.equals("USA")||
            countryName.equals("AUS")||
                countryName.equals("CHI")||
                countryName.equals("JPN")) {

            User user = new User();
            //set its attributes
            user.setUsername(username);
            user.setPassword(password);

            Country country = new Country();

            if(countryName.equals("IND")){
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
            }
            if(countryName.equals("USA")){
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
            }
            if(countryName.equals("AUS")){
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
            }
            if(countryName.equals("CHI")){
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
            }
            if(countryName.equals("JPN")){
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
            }
            country.setUser(user);
            user.setOriginalCountry(country);
            user.setConnected(false);


            String IP = country.getCode()+"."+userRepository3.save(user).getId();
            user.setOriginalIp(IP);

            userRepository3.save(user);

            return user;
        }
        else{
            throw new Exception("Country not found");
        }
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {

        User user = userRepository3.findById(userId).get();

        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).get();
        //set foreign key attributes
        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);

        serviceProviderRepository3.save(serviceProvider);


        return user;
    }
}
