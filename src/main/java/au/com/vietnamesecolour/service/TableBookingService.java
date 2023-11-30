package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.TableBookingDTO;

import java.text.ParseException;

public interface TableBookingService {

    ResponseData<TableBookingDTO> createTableBooking(TableBookingDTO payload) throws ParseException;

    ResponseData<TableBookingDTO> updateTableBooking(Integer id, TableBookingDTO payload) throws ParseException;

    ResponseData<Void> deleteTableBookingById(Integer id);

    ResponseData<TableBookingDTO> getTableBookingById(Integer id);

    ResponseData<ResponsePage<TableBookingDTO>> findTableBooking(
            String customerName,
            String mobileNumber,
            String email,
            String bookingDateFrom,
            String bookingDateTo,
            Integer page,
            Integer pageSize
    ) throws ParseException;

}
