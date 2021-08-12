const axios = require('axios').default

export default {
  namespaced: true,
  state: {
    consumers: []
  },
  mutations: {
    
  },
  actions: {
   async listar_domiciliarios({rootState,dispatch},domiciliario){
    console.log(dispatch)
        console.log(rootState.datos_sesion)
        rootState.domiciliarios.domiciliarios.push(domiciliario)
       /*
      
        try {
            let response = await dispatch('llamado_get',{
                url:"",
                tipo_header: "",
            })
            console.log(response)
        } catch (error) {
            console.log(error)
        }
        */
   },
   async llamado_get({dispatch},{url,tipo_header}){
       console.log(url)
       let header = await dispatch('generar_header',tipo_header)
       console.log(header)
       return new Promise ( (resolve,reject )  => {
                 axios.get(url,
                {
                    headers: header

                }).then((response) => {
                    console.log(response)
                    resolve(response)
                })
                .catch((error) => {
                    reject(new Error(error));
                })
       })
    },
  
   async llamado_post ({dispatch},{url,body,tipo_header}){
       console.log(url)
       console.log(body)
       
       let header = await dispatch('generar_header',tipo_header)
        
       
       console.log(url,body,header)
       return new Promise ( (resolve,reject )  => {
            
                axios.post(url,
                body,
                {
                    headers: header,

                }).then((response) => {
                    console.log(response)
                    resolve(response)
                })
                .catch((error) => {
                    reject(new Error(error));
                })
       })
    },
    async llamado_delete ({dispatch},{url,tipo_header}){
        console.log(url)
        
        let header = await dispatch('generar_header',tipo_header)
         
        
        console.log(url,header)
        return new Promise ( (resolve,reject )  => {
             
                 axios.delete(url,
                 {
                     headers: header,
 
                 }).then((response) => {
                     console.log(response)
                     resolve(response)
                 
                     
                 })
                 .catch((error) => {
                     reject(new Error(error));
                 })
        })
    },
    async llamado_patch ({dispatch},{url,body,tipo_header}){
        console.log(url)
        
        let header = await dispatch('generar_header',tipo_header)
        console.log(url,body,header)
        return new Promise ( (resolve,reject )  => {
             
                 axios.patch(url,
                 body,
                 {
                     headers: header,
 
                 }).then((response) => {
                     console.log(response)
                     resolve(response)
                 
                     
                 })
                 .catch((error) => {
                     reject(new Error(error));
                 })
        })
    },
    async llamado_put ({dispatch},{url,body,tipo_header}){
        console.log(url)
        
        let header = await dispatch('generar_header',tipo_header)
        console.log(url,body,header)
        return new Promise ( (resolve,reject )  => {
             
                 axios.put(url,
                 body,
                 {
                     headers: header,
 
                 }).then((response) => {
                     console.log(response)
                     resolve(response)
                 
                     
                 })
                 .catch((error) => {
                     reject(new Error(error));
                 })
        })
    },
   async generar_header({dispatch},tipo_header){

        let obj_header, header
        
        switch (tipo_header) {
            case 'parse':
                header = await dispatch('auth','parse')
                obj_header = {   
                    "Access-Control-Allow-Origin": "*",
                    'Content-Type' : 'application/json',
                    'X-Parse-REST-API-Key': 'LASDK823JKHR87SDFJSDHF8DFHASFDF',
                    'X-Parse-Application-Id': 'KSDJFKASJFI3S8DSJFDH',
                    'Authorization': header
                }
                break;
                default: 
                break;
        }
          return obj_header
    },
    auth({state},tipo){
        console.log(state)
        console.log(tipo)
        let dateNow=new Date();
        let dateUTC=new Date(dateNow.getUTCFullYear(), dateNow.getUTCMonth(), dateNow.getUTCDate(), dateNow.getUTCHours(), dateNow.getUTCMinutes(), dateNow.getUTCSeconds())

        // Definimos la diferencia en horas del time zone
        // Para la diferencia horaria de dos horas y media seria 2.5
        let tz=-5;
        // Calculamos los segundos de la zona horaria
        let seconds=(tz*60*60)*1000;
      
        // Aplicamos la diferencia horaria añadiendo los segundos al timestamp de la
        // fecha UTC
       var hora = dateUTC.setTime(dateUTC.getTime()+seconds)
     
        let payload = {
            "iss": "",
            "aud": "",
            "iat": hora,
            "exp": hora+120000,
           
            "sub": ""
        }
    // HS256 secrets are typically 128-bit random strings, for example hex-encoded:
    var jwt = require('jwt-simple');
    var secret
    if(tipo=='parse'){
        secret = '1'
      
    }else{
        secret = '2'
    }
    
   
     let header = 'Bearer '+ jwt.encode(payload, secret)
     console.log(header)
     return header
  
    },
  },
}
