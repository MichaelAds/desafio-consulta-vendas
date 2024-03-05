package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SellerSumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(value = "minDate", defaultValue = "", required = false) String minDate,
													  @RequestParam(value = "maxDate", defaultValue = "", required = false) String maxDate,
													  @RequestParam(value = "name", defaultValue = "", required = false) String name,
													  Pageable pageable) {
		Page<SaleMinDTO> list = service.findSales(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerSumDTO>> getSummary(@RequestParam(value = "minDate", defaultValue = "") String minDate,
														 @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		List<SellerSumDTO> list = service.findAll(minDate, maxDate);
		return ResponseEntity.ok(list);
	}
}
