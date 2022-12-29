<template>
  <div>
    <a-tag color="blue">Cantidad de empleados</a-tag>
    <a-badge :count="countEmployee" />
    <br />
    <br />
    <EmployeeFormVue 
    :name="'Registrar'" 
    :roles="roleList" 
    :areas="areaList" 
    @update-list="fetchEmployees" /> 
    <hr>

    <a-table 
    bordered 
    :data-source="listEmployee" 
    :columns="columns"
    :scroll="{ x: 1500, y: 400 }">
      <template #bodyCell="{ column, text, record }">
        <template v-if="column.dataIndex === 'name'">
          <div class="editable-cell">
            <div class="editable-cell-text-wrapper">
              {{ text || ' ' }}
            </div>
          </div>
        </template>
        <template v-else-if="column.dataIndex === 'operation'">
          <a-popconfirm v-if="listEmployee.length" :title="'¿Está seguro que quiere eliminar a ' + record.name"
            @confirm="onDelete(record.employeeId)">
            <a-radio-button value="large" >
              <delete-outlined />
            </a-radio-button>
          </a-popconfirm>
          <EmployeeFormVue 
          :name="'Editar'" 
          :record="record" 
          :roles="roleList" 
          :areas="areaList"
          @update-list="fetchEmployees" />
        </template>
      </template>
    </a-table>
    <a-pagination v-model:current="currentPage" :total="50" show-less-items />
  </div>
</template>

<script setup>
import EmployeeFormVue from '../components/EmployeeForm.vue';
import axios from "axios";
import { ref, reactive, computed, onMounted } from 'vue';
import {DeleteOutlined} from '@ant-design/icons-vue';
import { getAllEmployees, removeEmployee } from '../composables/Employee';
import { getAllRoles } from '../composables/Role';
import {getAllAreas} from '../composables/Area';

const columns = [
  {
    title: 'ID',
    dataIndex: 'employeeId',
    fixed: 'left',
    width: 50,
    key: 'employeeId',
  },
  {
    title: 'Nombre',
    dataIndex: 'name',
    fixed: 'left',
    width: 100,
    key: 'name'
  },
  {
    title: 'Apellido',
    dataIndex: 'lastName',
    fixed: 'left',
    width: 100,
    key: 'lastName'
  },
  {
    title: 'Email',
    dataIndex: 'email',
    width: 130,
    key: '1'
  },
  {
    title: 'Estado',
    dataIndex: 'status',
    width: 130,
    key: '2'
  },
  {
    title: 'ÁREA',
    dataIndex: 'nameArea',
    width: 130,
    key: '3'
  },
  {
    title: 'ROL',
    dataIndex: 'nameRole',
    width: 130,
    key: '4'
  },
  {
    title: 'Creado en',
    dataIndex: 'createdAt',
    width: 130,
    key: '5',
  },
  {
    title: 'Operaciones',
    dataIndex: 'operation',
    fixed: 'right',
    width: 100,
    key: 'operation'
  },
];

const translation = {
  "ENABLE": "HABILITADO",
  "UNABLE": "INHABILITADO",
  "UNDEFINED" : "INDEFINIDO",
  "FIRED" : "DESPEDIDO"
};

const editableData = reactive({});

const roleList = ref([]);
const areaList = ref([]);
const listEmployee = ref([]);

const currentPage = ref(2);

const countEmployee = computed(() => listEmployee.value.length);

const edit = async (id) => {
  const { data } = await axios.get(`/api/employee/${id}`);

};
const save = (key) => {
  Object.assign(dataSource.value.filter(item => key === item.key)[0], editableData[key]);
  delete editableData[key];
};

const onDelete = async (employeeId) => {
  try {
    const result = await removeEmployee(employeeId);
    await fetchEmployees();
  }
  catch (e) {
    if (e.response) {
      const data = await e.response.json();
      console.log(data.message);
    }

  }
};

const fetchEmployees = async () => {
  const result = await getAllEmployees();
  const {content} = await result.json();
  listEmployee.value = content.map(item => {
    item.status = translation[item.status];
    return item;
  });
}

onMounted(async () => {
  await fetchEmployees();
  //recibiendo roles
  let result = await getAllRoles();
  let data = await result.json();
  roleList.value = data;

  //recibiendo areas
  result = await getAllAreas();
  data = await result.json();
  areaList.value = data;

});

</script>


