package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.TableBookingStatusDTO;

public interface TableBookingStatusService {

    ResponseData<TableBookingStatusDTO> createTableBookingStatus(TableBookingStatusDTO payload);
    ResponseData<TableBookingStatusDTO> updateTableBookingStatus(Integer id, TableBookingStatusDTO payload);
    ResponseData<Void> deleteTableBookingStatusById(Integer id);
    ResponseData<TableBookingStatusDTO> getTableBookingStatusById(Integer id);
    ResponseData<ResponsePage<TableBookingStatusDTO>> findTableBookingStatus(String bookingStatusName, Integer page, Integer pageSize);
}
