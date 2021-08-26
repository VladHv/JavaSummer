package com.project.cruiser.repository;

import com.project.cruiser.entity.BookingList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingListRepository extends PagingAndSortingRepository<BookingList, Long> {

}
