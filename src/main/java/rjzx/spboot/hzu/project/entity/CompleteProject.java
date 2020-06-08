package rjzx.spboot.hzu.project.entity;

public class CompleteProject<T> {
    private Project project;
    private T projectother;

    public CompleteProject(Project project,T projectother){
        this.project=project;
        this.projectother=projectother;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public T getProjectother() {
        return projectother;
    }

    public void setProjectother(T projectother) {
        this.projectother = projectother;
    }
}
