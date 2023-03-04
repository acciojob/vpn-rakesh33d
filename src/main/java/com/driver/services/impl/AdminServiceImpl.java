package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin = new Admin();
        //set attributes before saving
        admin.setUsername(username);
        admin.setPassword(password);

        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin = adminRepository1.findById(adminId).get();
        //set attributes
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(providerName);
        //update the list of service providers in admin
        admin.getServiceProviders().add(serviceProvider);

        adminRepository1.save(admin);//child will automatically be saved
        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{

        if(countryName.equals("IND") ||
                countryName.equals("USA") ||
                countryName.equals("AUS") ||
                countryName.equals("CHI") ||
                countryName.equals("JPN") ){

            ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();

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
            serviceProviderRepository1.save(serviceProvider);
            return serviceProvider;
        }else{
            throw new Exception("Country not found");
        }

    }
}
