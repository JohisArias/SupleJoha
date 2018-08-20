/**
 * Automovil.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre:{
      type:"string"
    },
    marca:{
      type:"string"
    },
    color:{
      type:"string"
    },
    tipo:{
      type:"string"
    },
    anio:{
      type:"number"
    },
    precio:{
      type:"string"
    },
    numeroDuenios:{
      type: "number"
    },
    matriculadoActual:{
      type:"number"
    },
  },
};


