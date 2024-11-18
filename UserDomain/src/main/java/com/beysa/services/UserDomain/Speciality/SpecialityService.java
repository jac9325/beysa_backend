package com.beysa.services.UserDomain.Speciality;

import java.util.List;

public interface SpecialityService {
    List<Speciality> getAllSpeciality();
    List<Speciality> addSpecialities(List<Speciality> specialities);
}
