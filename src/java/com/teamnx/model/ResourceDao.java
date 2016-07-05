/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import java.util.ArrayList;

/**
 *
 * @author Y400
 */
public interface ResourceDao {

    public boolean insert(Resource resource);

    public boolean delete(String id);

    public boolean delete(Resource resource);

    public boolean updatePath(Resource resource);

    public boolean updateName(Resource resource);

    public Resource findResourceById(String resourceId);

    public ArrayList<Resource> findCourseResources(String courseId);

    public ArrayList<Resource> findChilds(Resource resource);

    public ArrayList<Resource> findChildsByFolderId(String folderId);
}
