package org.penguin.boot.service;

import org.penguin.boot.model.Organization;

import java.util.List;

public interface OrganizationService {

    Organization getOrganizationsById(Long id);

    List<Organization> getOrganizationsByName(String name);

    int insertOrganization(Organization organization);

    int deleteOrganization(Long id);

    int insertOrganizationList(List<Organization> organizations);

    List<Organization> getOrganizationsBuNameAndStatus(String name, String status);

}
