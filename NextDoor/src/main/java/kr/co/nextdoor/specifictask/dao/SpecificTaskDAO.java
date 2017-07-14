package kr.co.nextdoor.specifictask.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.nextdoor.file.dto.FileDTO;
import kr.co.nextdoor.specifictask.dto.SpecificTaskDTO;
import kr.co.nextdoor.specifictask.dto.SpecificTaskModiDTO;
import kr.co.nextdoor.task.dto.TaskDTO;

/*
* @Class : SpecificTaskDAO
* @Date : 2017. 06. 16
* @Author : 문창균
* @Desc : 세부업무에 대한 DAO
*/
public interface SpecificTaskDAO {
	/*
    * @method Name : insertSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무 추가
    * @return : int
    */
	public int insertSpecifictask(SpecificTaskDTO specifictaskdto);
	/*
    * @method Name : listSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무리스트 출력
    * @return : List<SpecificTaskDTO>
    */
	public List<SpecificTaskDTO> listSpecifictask(String task_no);
	/*
    * @method Name : updateSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무 수정
    * @return : int
    */
	public int updateSpecifictask(SpecificTaskModiDTO specifictaskmodidto);
	/*
    * @method Name : deleteSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무 삭제
    * @return : int
    */
	public int deleteSpecifictask(String specifictask_no);
	/*
    * @method Name : insertModiSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무수정값 추가
    * @return : int
    */
	public int insertModiSpecifictask(SpecificTaskModiDTO specifictaskmodidto); 	
	/*
    * @method Name : updateModiSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무수정값 업데이트
    * @return : int
    */
	public int updateModiSpecifictask(SpecificTaskModiDTO specifictaskmodidto);
	/*
    * @method Name : updateModiSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무수정값 업데이트
    * @return : int
    */
	public int updateSpecifictask(SpecificTaskDTO specifictaskdto);
	/*
    * @method Name : detailModiSpecifictask
    * @date : 2017. 06. 16
    * @author : 문창균
    * @description : 세부업무수정값 출력
    * @return : SpecificTaskModiDTO
    */
	public SpecificTaskModiDTO detailModiSpecifictask(String specifictask_no);
	/*
    * @method Name : seleteSpecificTaskComp
    * @date : 2017. 06. 23
    * @author : 문창균
    * @description : 세부업무 작업 완료 여부 확인
    * @return : SpecificTaskDTO
    */
	public SpecificTaskDTO seleteSpecificTaskComp(String specifictask_no);
	/*
    * @method Name : checkSpecifcitask
    * @date : 2017. 06. 23
    * @author : 문창균
    * @description : 세부업무 작업 완료 수정
    * @return : int
    */
	public int checkSpecifcitask(SpecificTaskDTO specifictaskdto);
	public List<TaskDTO> personalspecifictask(String task_no, String member_id);
	/*
    * @method Name : selectTaskdeadline
    * @date : 2017. 06. 26
    * @author : 문창균, 송지은
    * @description : 마감 1일전 메일 발송
    * @return : ArrayList<SpecificTaskModiDTO>
    */
	public ArrayList<SpecificTaskModiDTO> selectTaskdeadline();
	/*
    * @method Name : selectSpecificTask
    * @date : 2017. 06. 29
    * @author : 문창균
    * @description : 세부업무 생성후 세부업무 번호 추출
    * @return : SpecificTaskDTO
    */
	public SpecificTaskDTO selectSpecificTask(String task_no);
	
}
