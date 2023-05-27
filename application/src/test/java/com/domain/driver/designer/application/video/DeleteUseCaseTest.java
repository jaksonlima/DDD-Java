package com.domain.driver.designer.application.video;

import com.domain.driver.designer.application.UseCaseTest;
import com.domain.driver.designer.application.video.delete.DefaultDeleteVideoUseCase;
import com.domain.driver.designer.domain.video.VideoGateway;
import com.domain.driver.designer.domain.video.VideoID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DeleteUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteVideoUseCase useCase;

    @Mock
    private VideoGateway videoGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(videoGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteVideo_ShouldDeleteIt() {
        final var expectedId = VideoID.unique();

        doNothing()
                .when(videoGateway).deleteById(any());

        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));

        verify(videoGateway).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenCallsDeleteVideo_ShouldBeOk() {
        final var expectedId = VideoID.from("323");

        doNothing()
                .when(videoGateway).deleteById(any());

        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));

        verify(videoGateway).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenCallsDeleteVideoAndGatewayThrowsExpcetion_ShouldReceiveExption() {
        final var expectedId = VideoID.from("323");

        doThrow(new RuntimeException("Error when trying to delete video"))
                .when(videoGateway).deleteById(any());

        Assertions.assertThrows(RuntimeException.class, () -> this.useCase.execute(expectedId.getValue()));

        verify(videoGateway).deleteById(eq(expectedId));
    }

}
