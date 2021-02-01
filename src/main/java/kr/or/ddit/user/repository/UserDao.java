package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {

	//반환타입 메소드명(); --> public는 자동생성
	List<UserVo> selectAllUser();

	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
	//페이징 한 사용자 조회
	//List<UserVo> selectPagingUser(@Param("page")int page, @Param("pageSize")int pageSize);
	List<UserVo> selectPagingUser(PageVo vo);
	
	//사용자 전체 수 조회
	int selectAllUserCnt();
	
	//사용자 정보 수정
	//변경이 자주 있을 경우 캡슐화를 하는 것이 좋음
	int modifyUser(UserVo vo);
	
	int countUser(String userid);
	
	int registUser(UserVo vo);
	
	//사용자 삭제
	int deleteUser(String userid);
}
