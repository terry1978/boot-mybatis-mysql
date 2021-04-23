package org.penguin.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.penguin.boot.model.Organization;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    Organization getOrganizationsById(@Param("organizationId") Long id);

    List<Organization> getOrganizationsByName(@Param("name") String name);

    int insertOrganization(Organization organization);

    int deleteOrganizationById(Long id);

    int insertOrganizationList(List<Organization> organizations);

    List<Organization> getOrganizationsBuNameAndStatus(@Param("name") String name, @Param("status") String status);

}
