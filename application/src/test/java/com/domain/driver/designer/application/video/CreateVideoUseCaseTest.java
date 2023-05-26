package com.domain.driver.designer.application.video;

import com.domain.driver.designer.application.UseCaseTest;
import com.domain.driver.designer.application.video.create.DefaultCreateVideoUseCase;
import com.domain.driver.designer.domain.video.VideoGateway;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

public class CreateVideoUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateVideoUseCase useCase;

    @Mock
    private VideoGateway videoGateway;

    @Override
    protected List<Object> getMocks() {
        return null;
    }

}
