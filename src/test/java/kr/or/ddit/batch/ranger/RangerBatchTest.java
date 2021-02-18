package kr.or.ddit.batch.ranger;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/scheduler-context.xml", "classpath:/kr/or/ddit/config/spring/batch-context.xml", "classpath:/kr/or/ddit/config/spring/batch-test-context.xml"}) 
public class RangerBatchTest {

	@Autowired
	private JobLauncherTestUtils JobLauncherTestUtils; 
	
	@Test
	public void rangerTaskTest() throws Exception {
		
		//batch 테스트시 job타입으로 등록된 스프링 빈은 하나여야 한다
		//job 이름을 명시하지 않아도 container에 하나의 배치 job이 등록되어 있다는
		//가정이 있기 때문에 별도로 job 이름을 명시하지 않는다.
		JobExecution execution = JobLauncherTestUtils.launchJob();
		
		//종료상태를 반환한다
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus()); 
	}

}
