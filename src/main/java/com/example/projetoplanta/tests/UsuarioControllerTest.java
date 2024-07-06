// package com.example.projetoplanta.com.example.projetoplanta.tests;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
// import com.example.projetoplanta.com.example.projetoplanta.controllers.UsuarioController;
// import com.example.projetoplanta.com.example.projetoplanta.services.UsuarioService;
// import com.example.projetoplanta.com.example.projetoplanta.services.exceptions.UsuarioNaoEncontradoException;

// @RunWith(MockitoJUnitRunner.class)
// public class UsuarioControllerTest {

//     @Mock
//     private UsuarioService usuarioService;

//     @InjectMocks
//     private UsuarioController usuarioController;

//     @Test
//     public void testModificarUsuarioSucesso() {
//         // ...
//         Mockito.when(usuarioService.modificarUsuario(any(), any())).thenReturn(true);
//         // ...

//         ResponseEntity<Object> response = usuarioController.modificarUsuario("1", new UsuarioRecordDTO(...));

//         // ...
//         Mockito.verify(usuarioService).modificarUsuario(any(), any());
//         // ...

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals("Usuário modificado com sucesso.", response.getBody());
//     }

//     @Test
//     public void testModificarUsuarioUsuarioNaoEncontrado() {
//         // ...
//         Mockito.when(usuarioService.modificarUsuario(any(), any())).thenThrow(new UsuarioNaoEncontradoException());
//         // ...

//         ResponseEntity<Object> response = usuarioController.modificarUsuario("1", new UsuarioRecordDTO(...));

//         // ...
//         Mockito.verify(usuarioService).modificarUsuario(any(), any());
//         // ...

//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//         assertEquals("Usuário não encontrado.", response.getBody());
//     }

//     // ... testes para outros cenários de erro ...

// }

// }
