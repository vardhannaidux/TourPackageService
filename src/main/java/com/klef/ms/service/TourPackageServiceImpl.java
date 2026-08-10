package com.klef.ms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.ms.dto.TourPackageRequest;
import com.klef.ms.dto.TourPackageResponse;
import com.klef.ms.entity.TourPackage;
import com.klef.ms.exception.ResourceNotFoundException;
import com.klef.ms.repository.TourPackageRepository;
@Service
public  class TourPackageServiceImpl implements TourPackageService
{
 @Autowired
 private TourPackageRepository repository;
 
 @Override
 public TourPackageResponse saveTourPackage(TourPackageRequest request) {
	TourPackage tourPackage=TourPackage.builder()
			.name(request.getName())
			.type(request.getType())
			.status(request.getType())
			.location(request.getLocation())
			.build();
	TourPackage savedPackage=repository.save(tourPackage);
	return mapToResponse(savedPackage);
 }

 @Override
 public List<TourPackageResponse> getAllTourPackages() {
	// TODO Auto-generated method stub
	return repository.findAll()
			.stream()
			.map(this::mapToResponse)
			.collect(Collectors.toList());
			
 }

 @Override
 public TourPackageResponse getTourPackageById(Long id) {
	// TODO Auto-generated method stub
	 TourPackage tourPackage=repository.findById(id)
			 .orElseThrow(()->
			 new ResourceNotFoundException("Tour Package is Not Found with id: "+id));
	return mapToResponse(tourPackage);
 }

 @Override
 public TourPackageResponse updateTourPackage(Long id, TourPackageRequest request) {
	// TODO Auto-generated method stub
	 TourPackage tourPackage=repository.findById(id)
			 .orElseThrow(()->
			 new ResourceNotFoundException("Tour Package not found with id: "+id));
	 tourPackage.setName(request.getName());
	 tourPackage.setType(request.getType());
	 tourPackage.setStatus(request.getStatus());
	 tourPackage.setLocation(request.getLocation());
	 
	 TourPackage updatedPackage=repository.save(tourPackage);
	return mapToResponse(updatedPackage);
 }

 @Override
 public void deleteTourPackage(Long id) {
	// TODO Auto-generated method stub
	 TourPackage tourPackage =repository.findById(id)
			 .orElseThrow(()->
			 new ResourceNotFoundException("Tour Package not Found with the id: "+id));
	 repository.delete(tourPackage);
	 
	
 }

 @Override
 public List<TourPackageResponse> getTourPackagesByName(String name) {
	// TODO Auto-generated method stub
	return repository.findByName(name)
			.stream()
			.map(this::mapToResponse)
			.collect(Collectors.toList());
 }

 @Override
 public List<TourPackageResponse> getTourPackagesByType(String type) {
	// TODO Auto-generated method stub
	return repository.findByType(type)
			.stream()
			.map(this::mapToResponse)
			.collect(Collectors.toList());
 }
 
 private TourPackageResponse mapToResponse(TourPackage tourPackage) {
	 return TourPackageResponse.builder()
			 .tourPackageId(tourPackage.getToutPackageId())
			 .name(tourPackage.getName())
			 .type(tourPackage.getType())
			 .status(tourPackage.getStatus())
			 .location(tourPackage.getLocation())
			 .createdAt(tourPackage.getCreatedAt())
			 .updatedAt(tourPackage.getUpdatedAt())
			 .build();
			 
 }
 
}