<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <a-menu theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
        <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout v-if="token" style="min-height: 100vh">
      <a-layout-sider v-model:collapsed="collapsed" collapsible>
        <div class="logo" />
        <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
          <a-menu-item key="1">
            <pie-chart-outlined />
            <span>Option 1</span>
          </a-menu-item>
          <a-menu-item key="2">
            <desktop-outlined />
            <span>Option 2</span>
          </a-menu-item>
          <a-sub-menu key="sub1">
            <template #title>
              <span>
                <bulb-outlined />
                <span>Equipamento</span>
              </span>
            </template>
            <a-menu-item key="3">
              <RouterLink active-class="active" to="/category">
                Categoria
              </RouterLink>
            </a-menu-item>
            <a-menu-item key="4">
              <RouterLink active-class="active" to="/equipment">
                Equipamento
              </RouterLink>
            </a-menu-item>
            <a-menu-item key="5">
              <RouterLink active-class="active" to="/shelf">
                Estante
              </RouterLink>
            </a-menu-item>
            <a-menu-item key="6">
              <RouterLink active-class="active" to="/transaction">
                Transacciones
              </RouterLink>
            </a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub2">
            <template #title>
              <span>
                <team-outlined />
                <span>Administración</span>
              </span>
            </template>
            <a-menu-item key="7">
              <RouterLink active-class="active" to="/area">Area</RouterLink>
            </a-menu-item>
            <a-menu-item key="8">
              <RouterLink active-class="active" to="/role">Rol</RouterLink>
            </a-menu-item>
            <a-menu-item key="9">
              <RouterLink active-class="active" to="/employee">Empleado</RouterLink>
            </a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub3">
            <template #title>
              <span>
                <user-outlined />
                <span>Propietario</span>
              </span>
            </template>
            <a-menu-item key="10">
              <RouterLink active-class="active" to="/type_owner">Tipo propietario</RouterLink>
            </a-menu-item>
            <a-menu-item key="11">
              <RouterLink active-class="active" to="/owner">Propietario</RouterLink>
            </a-menu-item>
          </a-sub-menu>
          <a-menu-item key="12">
            <file-outlined />
            <span>File</span>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout>
        <!-- <a-layout-header style="background: #fff; padding: 0" /> -->
        <a-layout-content style="margin: 0 16px">
          <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>User</a-breadcrumb-item>
            <a-breadcrumb-item>Bill</a-breadcrumb-item>
          </a-breadcrumb>
          <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
            <RouterView />
          </div>
        </a-layout-content>
        <a-layout-footer style="text-align: center">
          Ant Design ©2018 Created by Ant UED
        </a-layout-footer>
      </a-layout>
    </a-layout>
    <div v-else class="mx-auto margin w-50">
      <a-form :model="formState" name="basic" :label-col="{ span: 8 }" :wrapper-col="{ span: 16 }" autocomplete="off"
        :validate-messages="validateMessages" class="">

        <a-form-item label="Email" name="email" :rules="[{ type: 'email' }]">
          <a-input v-model:value="formState.email" />
        </a-form-item>

        <a-form-item label="Password" name="password"
          :rules="[{ required: true, message: 'Porfavor, ingrese su contraseña' }]">
          <a-input-password v-model:value="formState.password" />
        </a-form-item>

        <!-- <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
          <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
        </a-form-item> -->

        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" v-on:click="logIn">Ingresar</a-button>
        </a-form-item>
      </a-form>
    </div>
  </a-layout>

  <!-- <RouterView v-else /> -->



</template>

<script setup>
import { reactive, watch, ref, onMounted } from 'vue';
import {
  PieChartOutlined,
  DesktopOutlined,
  BulbOutlined,
  TeamOutlined,
  FileOutlined,
  UserOutlined
} from '@ant-design/icons-vue';
import { login } from './composables/Auth';
import { useRouter } from 'vue-router';
import { successNotification, errorNotification } from './composables/Notification';

const router = useRouter();

const collapsed = ref(false);
const selectedKeys = ref(['1']);
const token = ref(null); //to check if there's a token or not

const validateMessages = {
  required: '${label} is required!',
  types: {
    email: '${label} no es un email válido!',
    number: '${label} is not a valid number!',
  },
  number: {
    range: '${label} must be between ${min} and ${max}',
  },
};

const formState = reactive({
  email: '',
  password: '',
  remember: false,
});

const logIn = async () => {
  const user = { ...formState };
  try {
    const result = await login(user);
    const data = await result.text();
    localStorage.setItem('token', data);
    localStorage.setItem('email', user.email);
    token.value = localStorage.getItem('token');
    router.push({ name: 'equipment' });
    clearLogin();
  }
  catch (e) {
    if (e.response) {
      const data = await e.response.json();
      if (data.errors) {
        errorNotification("Ocurrió un error :(", data.errors[0].defaultMessage);
        return;
      }
      errorNotification("Ocurrió un error al iniciar sesión :(", data.message);
    }
  }

};

const clearLogin = () => {
  formState.email = '';
  formState.password = '';
  formState.remember = false;
}

onMounted(() => {
  token.value = localStorage.getItem('token');

});

</script>

<style scoped>
#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}

[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}
</style>

<style scoped>
.margin {
  margin-top: 16px;
}
</style>
