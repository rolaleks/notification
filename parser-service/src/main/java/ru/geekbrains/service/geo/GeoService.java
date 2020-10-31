package ru.geekbrains.service.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.*;

@Service
public class GeoService {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private StreetService streetService;

    @Autowired
    private AddressService addressService;

    public Address detectAddress(@NonNull String country, String region, @NonNull String city, String district, @NonNull String street, String house) {

        Address address = new Address();

        Country countryModel = countryService.findOrCreate(country);


        City cityModel = this.detectCity(countryModel, region, city);
        Street streetModel = this.detectStreet(cityModel, district, street);

        address.setStreet(streetModel);
        address.setHouse(house);
        addressService.save(address);

        return address;
    }

    public City detectCity(@NonNull Country countryModel, String region, @NonNull String city) {

        Region regionModel = null;

        if (region != null) {
            regionModel = regionService.findOrCreate(region, countryModel);
        }

        return cityService.findOrCreate(city, countryModel, regionModel);
    }

    public Street detectStreet(@NonNull City cityModel, String district, @NonNull String street) {


        District districtModel = null;

        if (district != null) {
            districtModel = districtService.findOrCreate(district, cityModel);
        }

        return streetService.findOrCreate(street, cityModel, districtModel);
    }
}
