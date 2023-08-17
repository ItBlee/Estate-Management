package com.itblee.service.impl;

import com.itblee.converter.RoleConverter;
import com.itblee.dto.RoleDTO;
import com.itblee.repository.RoleRepository;
import com.itblee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;

	public List<RoleDTO> findAll() {
		return roleRepository.findAll().stream()
				.map(roleConverter::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Map<String, String> getRoles() {
		return roleRepository.findAll().stream()
				.map(roleConverter::toDto)
				.collect(Collectors.toMap(RoleDTO::getCode, RoleDTO::getName));
	}
}
