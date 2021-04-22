package org.penguin.boot.service.impl;

import org.penguin.boot.mapper.OrganizationMapper;
import org.penguin.boot.model.Organization;
import org.penguin.boot.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public List<Organization> getOrganizationsByName(String name) {
        return organizationMapper.getOrganizationsByName(name);
    }

    @Override
    public int insertOrganization(Organization organization) {
        return organizationMapper.insertOrganization(organization);
    }

    @Override
    public int deleteOrganization(Long id) {
        return organizationMapper.deleteOrganizationById(id);
    }

    @Override
    public int insertOrganizationList(List<Organization> organizations) {
        return organizationMapper.insertOrganizationList(organizations);
    }

    @Override
    public List<Organization> getOrganizationsBuNameAndStatus(String name, String status) {
        return organizationMapper.getOrganizationsBuNameAndStatus(name, status);
    }

}
