/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamnx.model;

import com.teamnx.db.DbcpBean;
import com.teamnx.util.MD5;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Y400
 */
public class ResourceDaoImpl implements ResourceDao {

    private DbcpBean dbcpBean;

    @Override
    public boolean insert(Resource resource) {
	Connection connection = dbcpBean.getConnection();
	String sql = "INSERT INTO resource values(?,?,?,?,?,?,?)";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getId());
	    ps.setString(2, resource.getFatherId());
	    ps.setString(3, resource.getName());
	    ps.setString(4, resource.getPath());
	    ps.setString(5, resource.getTeacherId());
	    ps.setTimestamp(6, new Timestamp(resource.getLastChange()));
	    ps.setBoolean(7, resource.isFolder());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;

	} finally {
	    dbcpBean.shutDownDataSource();
	}

    }

    @Override
    public boolean delete(String id) {
	Connection connection = dbcpBean.getConnection();
	String sql = "delete from resource where id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;

	} finally {
	    dbcpBean.shutDownDataSource();
	}
    }

    @Override
    public boolean delete(Resource resource) {
	Connection connection = dbcpBean.getConnection();
	String sql = "delete from resource where id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getId());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;

	} finally {
	    dbcpBean.shutDownDataSource();
	}
    }

    @Override
    public boolean updatePath(Resource resource) {
	Connection connection = dbcpBean.getConnection();
	String sql = "update resource set fath = ?,path= ?  where id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getFatherId());
	    ps.setString(2, resource.getPath());
	    ps.setString(3, resource.getId());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;

	} finally {
	    dbcpBean.shutDownDataSource();
	}
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public boolean updateName(Resource resource) {
	Connection connection = dbcpBean.getConnection();
	String sql = "update resource set name=?  where id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getName());
	    ps.setString(2, resource.getId());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;

	} finally {
	    dbcpBean.shutDownDataSource();
	}
    }

    @Override
    public ArrayList<Resource> findCourseResources(String courseId) {

	ArrayList<Resource> resources = new ArrayList<Resource>();
	Connection connection = dbcpBean.getConnection();
	String sql = "select * from resource where fatherId=?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, MD5.Md5_16(courseId + "_root"));
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Resource r = new Resource();
		r.setFatherId(rs.getString("fatherId"));
		r.setFolder(rs.getBoolean("folder"));
		r.setId(rs.getString("id"));
		r.setLastChange(rs.getTimestamp("lastChange").getTime());
		r.setName(rs.getString("name"));
		r.setPath(rs.getString("path"));
		r.setTeacherId(rs.getString("teacherId"));
		resources.add(r);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    dbcpBean.shutDownDataSource();
	    return resources;
	}

    }

    @Override
    public ArrayList<Resource> findChilds(Resource resource) {
	ArrayList<Resource> resources = new ArrayList<Resource>();
	Connection connection = dbcpBean.getConnection();
	String sql = "select * from resource where fatherId=?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getName());
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Resource r = new Resource();
		r.setFatherId(rs.getString("fatherId"));
		r.setFolder(rs.getBoolean("folder"));
		r.setId(rs.getString("id"));
		r.setLastChange(rs.getTimestamp("lastChange").getTime());
		r.setName(rs.getString("name"));
		r.setPath(rs.getString("path"));
		r.setTeacherId(rs.getString("teacherId"));
		resources.add(r);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    dbcpBean.shutDownDataSource();
	    return resources;
	}
    }

    @Override
    public ArrayList<Resource> findChildsByFolderId(String folderId) {
	ArrayList<Resource> resources = new ArrayList<Resource>();
	Connection connection = dbcpBean.getConnection();
	String sql = "select * from resource where fatherId=?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, folderId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Resource r = new Resource();
		r.setFatherId(rs.getString("fatherId"));
		r.setFolder(rs.getBoolean("folder"));
		r.setId(rs.getString("id"));
		r.setLastChange(rs.getTimestamp("lastChange").getTime());
		r.setName(rs.getString("name"));
		r.setPath(rs.getString("path"));
		r.setTeacherId(rs.getString("teacherId"));
		resources.add(r);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    dbcpBean.shutDownDataSource();
	    return resources;
	}
    }

    @Override
    public Resource findResourceById(String resourceId) {
	Resource resource = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "select * from resource where id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resourceId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		resource = new Resource();
		resource.setId(rs.getString("id"));
		resource.setName(rs.getString("name"));
		resource.setPath(rs.getString("path"));
		resource.setFatherId(rs.getString("fatherId"));
		resource.setTeacherId(rs.getString("teacherId"));
		resource.setLastChange(rs.getTimestamp("lastChange").getTime());
		resource.setFolder(rs.getBoolean("folder"));

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	}
	return resource;
    }

}
