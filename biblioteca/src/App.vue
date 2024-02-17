<template>
  <div class="container">
    <ModalRegistroVue />
    <b-row class="justify-content-center">
      <div
        v-for="(libro, index) in libros"
        :key="index"
        class="col-sm-12 col-md-6 col-lg-4 mb-2"
      >
        <b-card
          :title="libro.titulo"
          :img-src="'http://localhost:8080/' + libro.imagen"
          img-alt="Image"
          img-top
          tag="article"
          img-height="500px"
          style="max-width: 25rem; height: 800px"
        >
          <div class="card-body">
            <b-card-text class="mb-2 text-secondary">
              <strong
                ><h3>{{ libro.nombre }}</h3></strong
              >
            </b-card-text>
            <b-card-text class="mb-2">
              <strong>Autor:</strong> {{ libro.autor }}
            </b-card-text>
            <b-card-text class="mb-2">
              <strong>Género:</strong> {{ libro.genero.nombre }}
            </b-card-text>
            <b-card-text class="mb-2">
              <strong>Año de publicación:</strong> {{ libro.anioPublicacion }}
            </b-card-text>
          </div>

          <div class="d-flex justify-content-end">
            <b-button variant="danger" @click="confirmarEliminar(libro.id)">
              <b-icon-trash-fill></b-icon-trash-fill>
            </b-button>
            <b-button variant="success" @click="abrirModalActualizar(libro)">
              <b-icon-pencil-square></b-icon-pencil-square>
            </b-button>
          </div>
        </b-card>
      </div>
    </b-row>

    <ModalActualizarLibro ref="modalActualizarLibro" />
  </div>
</template>

<script>
import ModalRegistroVue from "./components/ModalRegistro.vue";
import ModalActualizarLibro from "./components/ModalActualizarLibro.vue";

import axios from "axios";
import Swal from "sweetalert2";

export default {
  components: {
    ModalRegistroVue,
    ModalActualizarLibro
  },
  data() {
    return {
      libros: [],
    };
  },
  mounted() {
    // Llamada a la API al montar el componente
    this.obtenerLibros();
  },
  methods: {
    async obtenerLibros() {
      try {
        // Realizar la petición GET a la API
        const response = await axios.get("http://localhost:8080/api/libros/");
        // Almacenar los libros en el estado del componente
        this.libros = response.data.data;
      } catch (error) {}
    },
    abrirModalActualizar(libro) {
      // Acceder al método abrirModal del componente ModalActualizarLibro mediante la referencia ref
      this.$refs.modalActualizarLibro.abrirModal(libro);
    },

    confirmarEliminar(libroId) {
      Swal.fire({
        title: "¿Esta seguro?",
        text: "¡Esta accion no podra revertirse!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#ffc107",
        cancelButtonColor: "#d33",
        confirmButtonText: "Confirmar",
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          eliminar(libroId);
        }
      });
    },
  },
};

const eliminar = async (libroId) => {
  try {
    const response = await axios.delete("http://localhost:8080/api/libros/", {
      data: { id: libroId },
    });

    const responseData = response.data;
    if (responseData.statusCode < 400) {
      Swal.fire({
        title: "Acción exitosa",
        text: "El registro se ha eliminado",
        icon: "success",
      });
    } else {
      Swal.fire({
        title: "Upps!",
        text: responseData.message,
        icon: "error",
      });
    }
  } catch (error) {
    Swal.fire({
      title: "Algo salió mal",
      text: "Favor de intentarlo mas tarde",
      icon: "error",
    });
  }
};
</script>
