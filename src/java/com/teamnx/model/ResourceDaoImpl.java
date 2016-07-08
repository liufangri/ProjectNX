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
	String sql = "INSERT INTO resource VALUES(?,?,?,?,?,?,?,?,?)";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getId());
	    ps.setString(2, resource.getFatherId());
	    ps.setString(3, resource.getName());
	    ps.setTimestamp(4, new Timestamp(resource.getLastChange()));
	    ps.setString(5, resource.getTeacherId());
	    ps.setString(6, resource.getTeacherName());
	    ps.setString(7, resource.getPath());
	    ps.setBoolean(8, resource.isFolder());
	    ps.setString(9, resource.getCourseId());
	    ps.executeUpdate();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
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
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    @Override
    public boolean delete(Resource resource) {
	Connection connection = dbcpBean.getConnection();
	String sql = "DELETE FROM resource WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getId());
	    ps.execute();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    return false;

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    @Override
    public boolean updatePath(Resource resource) {
	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE resource SET father_id = ?,path= ?  WHERE id = ?";
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
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    public void setDbcpBean(DbcpBean dbcpBean) {
	this.dbcpBean = dbcpBean;
    }

    @Override
    public boolean updateName(Resource resource) {
	Connection connection = dbcpBean.getConnection();
	String sql = "UPDATE resource SET name=?  WHERE id = ?";
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
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    @Override
    public ArrayList<Resource> findCourseResources(String courseId) {

	ArrayList<Resource> resources = new ArrayList<Resource>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELETE * FROM resource WHERE father_id=?";
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
		r.setTeacherId(rs.getString("teacher_id"));
		r.setTeacherName(rs.getString("teacher_name"));
		resources.add(r);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return resources;
	}

    }

    @Override
    public ArrayList<Resource> findChilds(Resource resource) {
	ArrayList<Resource> resources = new ArrayList<Resource>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELETE * FROM resource WHERE father_id=?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resource.getName());
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Resource r = new Resource();
		r.setFatherId(rs.getString("fatherId"));
		r.setFolder(rs.getBoolean("folder"));
		r.setId(rs.getString("id"));
		r.setLastChange(rs.getTimestamp("lastchange").getTime());
		r.setName(rs.getString("name"));
		r.setPath(rs.getString("path"));
		r.setTeacherId(rs.getString("teacher_id"));
		r.setTeacherName(rs.getString("teacher_name"));
		r.setCourseId(rs.getString("course_id"));

		resources.add(r);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return resources;
	}
    }

    @Override
    public ArrayList<Resource> findChildsByFolderId(String folderId) {
	ArrayList<Resource> resources = new ArrayList<Resource>();
	Connection connection = dbcpBean.getConnection();
	String sql = "SELETE * FROM resource WHERE father_id=?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, folderId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Resource r = new Resource();
		r.setFatherId(rs.getString("fatherId"));
		r.setFolder(rs.getBoolean("folder"));
		r.setId(rs.getString("id"));
		r.setLastChange(rs.getTimestamp("lastchange").getTime());
		r.setName(rs.getString("name"));
		r.setPath(rs.getString("path"));
		r.setTeacherId(rs.getString("teacher_id"));
		r.setTeacherName(rs.getString("teacher_name"));
		r.setCourseId(rs.getString("course_id"));
		resources.add(r);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return resources;
	}
    }

    @Override
    public Resource findResourceById(String resourceId) {
	Resource resource = null;
	Connection connection = dbcpBean.getConnection();
	String sql = "SELECT * FROM resource WHERE id = ?";
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, resourceId);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		resource = new Resource();
		resource.setFatherId(rs.getString("father_id"));
		resource.setFolder(rs.getBoolean("folder"));
		resource.setId(rs.getString("id"));
		resource.setLastChange(rs.getTimestamp("lastchange").getTime());
		resource.setName(rs.getString("name"));
		resource.setPath(rs.getString("path"));
		resource.setTeacherId(rs.getString("teacher_id"));
		resource.setTeacherName(rs.getString("teacher_name"));
		resource.setCourseId(rs.getString("course_id"));

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ResourceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		connection.close();
	    } catch (SQLException ex) {
		Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return resource;

	}
    }

    @Override
    public boolean deleteMultyResources(String[] ids) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
