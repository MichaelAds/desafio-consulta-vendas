package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.model.DateFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SellerSumDTO> findAll(String dateStart, String dateFinal) {
		DateFormater date = dateFormater(dateStart, dateFinal);

		List<SellerSumDTO> result = repository.searchByDateStartAndDateFinalAndName(date.getMinDate(), date.getMaxDate());
		return result;
	}

	public Page<SaleMinDTO> findSales(String dateStart, String dateFinal, String name, Pageable pageable) {
		DateFormater date = dateFormater(dateStart, dateFinal);

		return (Page<SaleMinDTO>) repository.searchByMinDateAndMaxDateAndName(date.getMinDate(), date.getMaxDate(), name, pageable);
	}

	private DateFormater dateFormater(String dateStart, String dateFinal) {
		LocalDate maxDate;
		LocalDate minDate;
		if(!dateFinal.isEmpty()) {
			maxDate = LocalDate.parse(dateFinal);
		} else {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		if(!dateStart.isEmpty()) {
			minDate = LocalDate.parse(dateStart);
		} else {
			minDate = maxDate.minusYears(1L);
		}

		return new DateFormater(minDate, maxDate);
	}
}
