/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author hoang
 */
public class ExamsDTO {
    private int exam_id;
    private String exam_tilte ;
    private String Subject ;
    private int category_id;
    private int toltal_marks ;
    private int Duration;

    public ExamsDTO() {
    }

    public ExamsDTO(int exam_id, String exam_tilte, String Subject, int category_id, int toltal_marks, int Duration) {
        this.exam_id = exam_id;
        this.exam_tilte = exam_tilte;
        this.Subject = Subject;
        this.category_id = category_id;
        this.toltal_marks = toltal_marks;
        this.Duration = Duration;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_tilte() {
        return exam_tilte;
    }

    public void setExam_tilte(String exam_tilte) {
        this.exam_tilte = exam_tilte;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getToltal_marks() {
        return toltal_marks;
    }

    public void setToltal_marks(int toltal_marks) {
        this.toltal_marks = toltal_marks;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    @Override
    public String toString() {
        return "Exams{" + "exam_id=" + exam_id + ", exam_tilte=" + exam_tilte + ", Subject=" + Subject + ", category_id=" + category_id + ", toltal_marks=" + toltal_marks + ", Duration=" + Duration + '}';
    }
    
    
}
