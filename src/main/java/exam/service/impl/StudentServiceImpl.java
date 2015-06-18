package exam.service.impl;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exam.dao.StudentDao;
import exam.dao.base.BaseDao;
import exam.model.role.Student;
import exam.service.StudentService;
import exam.service.base.BaseServiceImpl;
import exam.util.StringUtil;

@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
	
	private StudentDao studentDao;

	@Resource(name = "studentDao")
	@Override
	protected void setBaseDao(BaseDao<Student> baseDao) {
		super.baseDao = baseDao;
		this.studentDao = (StudentDao) baseDao;
	}
	
	public boolean isExisted(String id) {
		BigInteger result = (BigInteger) studentDao.queryForObject("select count(id) from student where id = " + id, BigInteger.class);
		return result.intValue() > 0;
	}
	
	public void update(String id, String name, int cid) {
		String sql = "update student set name = ?, cid = ? where id = ?";
		studentDao.update(sql, new Object[] {name, cid, id});
	}
	
	public void updatePassword(String id, String password) {
		String sql = "update student set password = ? where id = ?";
		studentDao.update(sql, new Object[] {StringUtil.md5(password), id});
	}

}
