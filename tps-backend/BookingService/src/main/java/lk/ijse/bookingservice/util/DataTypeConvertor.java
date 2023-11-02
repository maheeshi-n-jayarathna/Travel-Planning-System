package lk.ijse.bookingservice.util;

import lk.ijse.bookingservice.dto.BookingDTO;
import lk.ijse.bookingservice.dto.VehicleBookingDTO;
import lk.ijse.bookingservice.entity.Booking;
import lk.ijse.bookingservice.entity.VehicleBooking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public DataTypeConvertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Booking getBooking(BookingDTO bookingDTO) {
        return modelMapper.map(bookingDTO, Booking.class);
    }

    public BookingDTO getBookingDTO(Booking booking) {
        return modelMapper.map(booking, BookingDTO.class);
    }

    public VehicleBooking getVehicleBooking(VehicleBookingDTO vehicleBookingDTO) {
        return modelMapper.map(vehicleBookingDTO, VehicleBooking.class);
    }

    public VehicleBookingDTO getVehicleBookingDTO(VehicleBooking vehicleBooking) {
        return modelMapper.map(vehicleBooking, VehicleBookingDTO.class);
    }
}
