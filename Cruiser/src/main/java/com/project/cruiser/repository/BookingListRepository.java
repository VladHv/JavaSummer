package com.project.cruiser.repository;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingListRepository extends PagingAndSortingRepository<BookingList, Long> {

    @Query("SELECT b FROM BookingList b WHERE b.status = ?1")
    Page<BookingList> findAll(BookingStatus keyword, Pageable pageable);

}
