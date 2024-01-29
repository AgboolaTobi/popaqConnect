package org.popaqConnect.services.CourseApplication;

import org.popaqConnect.data.CourseStatus;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.repositories.CourseApplicationRepository;
import org.popaqConnect.dtos.requests.TrainingRequest;
import org.popaqConnect.dtos.requests.UpdateOnCourseApplicationRequest;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;
import org.popaqConnect.exceptions.CourseApplicationException;
import org.popaqConnect.exceptions.DateException;
import org.popaqConnect.exceptions.UpdateOnCourseException;
import org.popaqConnect.utils.GenerateId;
import org.popaqConnect.utils.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseApplicationImpl implements CourseApplicationService {
    @Autowired
    private CourseApplicationRepository courseApplicationRepository;
    @Override
    public ApplyForTrainingResponse setupCourseApplication(TrainingRequest request) {
        CourseApplication courseApplication = new CourseApplication();
        String generatedId = "TR-" +GenerateId.generateId();
        courseApplication.setAboutYou(request.getAboutYou());
        if(!Verification.verifyDate(request.getStartDate()))throw new DateException("Wrong date format");
        if(!Verification.verifyDate(request.getEndDate()))throw new DateException("Wrong date format");
        courseApplication.setDueDate(request.getEndDate());
        courseApplication.setCourseId(generatedId);
        courseApplication.setTrainerEmail(request.getTrainerEmail());
        courseApplication.setExpectedStartDate(request.getStartDate());
        courseApplication.setTraineeEmail(request.getTraineeEmail());
        courseApplicationRepository.save(courseApplication);
        ApplyForTrainingResponse trainingResponse = new ApplyForTrainingResponse();
        trainingResponse.setMessage(courseApplication.getCourseId());
        return trainingResponse;

     }

    @Override
    public CourseApplication findCourse(String courseCode, String email) {
        List<CourseApplication> allCourseForUser = findAllCourse(email);
        for(CourseApplication courseApplication: allCourseForUser){
            if(courseApplication.getCourseId().equals(courseCode))return courseApplication;
        }
        return null;
    }

    public List<CourseApplication> findAllCourse(String email){
        List<CourseApplication> allCourses = new ArrayList<>();
        for(CourseApplication courseApplication:courseApplicationRepository.findAll()){
            if(courseApplication.getTraineeEmail().equals(email))allCourses.add(courseApplication);
            if(courseApplication.getTrainerEmail().equals(email))allCourses.add(courseApplication);
        }
        return allCourses;
    }

    @Override
    public void updateCourseApplication(UpdateOnCourseApplicationRequest updateCourseRequest) {
        CourseApplication courseApplication = findCourse(updateCourseRequest.getCourseCode(), updateCourseRequest.getTraineeEmail());
        if(courseApplication == null)throw new UpdateOnCourseException("Invalid Details");
        if(courseApplication.getCourseStatus() == CourseStatus.LEARNING) {
            if (updateCourseRequest.getUpdateDueDate() != null) {
                courseApplication.setDueDate(updateCourseRequest.getUpdateDueDate());
            }
            if (updateCourseRequest.getUpdateCourseStatus() != null) {
                updateCourseStatus(updateCourseRequest, courseApplication);
                courseApplicationRepository.save(courseApplication);
            }
        }
    }

    @Override
    public void cancelCourse(String courseCode, String traineeEmail) {
        CourseApplication courseApplication = findCourse(courseCode,traineeEmail);
        if(courseApplication == null)throw new CourseApplicationException("Invalid Details");
        courseApplication.setCourseStatus(CourseStatus.CANCELED);
        courseApplicationRepository.save(courseApplication);

    }

    private static void updateCourseStatus(UpdateOnCourseApplicationRequest updateCourseRequest, CourseApplication courseApplication) {
        for(CourseStatus courseStatus:CourseStatus.values()){
            if(courseStatus.name().equalsIgnoreCase(updateCourseRequest.getUpdateCourseStatus())){
                courseApplication.setCourseStatus(courseStatus);

            }
        }
    }
}
