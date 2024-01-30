package lk.ijse.tps.tps.hotelservice.service;

import lk.ijse.tps.tps.hotelservice.dto.HotelDTO;
import lk.ijse.tps.tps.hotelservice.dto.HotelOptionDTO;

import java.util.List;


public interface HotelService {
    List<HotelDTO> getAllHotel();
    HotelDTO saveHotel(HotelDTO hotelDTO);
    void updateHotel(HotelDTO hotelDTO);
    void deleteHotel(String hotelId);
    HotelDTO getSelectedHotel(String hotelId);

    List<HotelOptionDTO> getAllHotelOption();
    HotelOptionDTO addHotelOption(HotelOptionDTO hotelOptionDTO);
    void updateHotelOption(HotelOptionDTO hotelOptionDTO);
    void deleteHotelOption(String hotelOptionId);
    HotelOptionDTO getSelectedHotelOption(String hotelOptionId);
}
