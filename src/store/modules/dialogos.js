export default {
    namespaced: true,
    state: {
      dialogo_respuesta: false,
      info_dialogo: {
          color: 'indigo',
          tipo: 'Error',
          mensaje: 'Mensaje por defecto'
      },
      load: false,
    },
    mutations: {
          cargando(state) {
              state.dialogo_cargando = true
          },
          respuesta(state, {color,tipo,mensaje} ){
              console.log(color,tipo,mensaje)
              state.info_dialogo.color = color
              state.info_dialogo.tipo = tipo
              state.info_dialogo.mensaje = mensaje
              
              state.dialogo_respuesta = true
          }
    },
    actions: {
      
     
    },
  }
  