package com.project.cruiser.repository;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import com.project.cruiser.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingListRepository extends CrudRepository<BookingList, Long> {

    @Query("SELECT b FROM BookingList b WHERE b.status = ?1")
    Page<BookingList> findAll(BookingStatus keyword, Pageable pageable);

    Page<BookingList> findAll(Pageable pageable);

    List<BookingList> findAll();

}
