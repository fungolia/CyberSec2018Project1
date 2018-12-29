package sec.project.repository;

import sec.project.domain.Signup;

public interface SignupRepositoryCustom {

    public Signup findByName(String name);
}
