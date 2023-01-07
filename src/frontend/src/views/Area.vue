<template>
    <div>
        <a-tag color="blue">Cantidad de Marcas registradas</a-tag>
        <a-badge :count="countArea" />
        <br />
        <!-- <br />
        <BrandFormVue @update-list="fetchBrands" /> -->
        <hr>

        <a-table bordered :data-source="listArea" :columns="columns">
            <template #bodyCell="{ column, text, record }">
                <template v-if="column.dataIndex === 'name'">
                    <div class="editable-cell">
                        <div class="editable-cell-text-wrapper">
                            {{ text || ' ' }}
                        </div>
                    </div>
                </template>
                <template v-else-if="column.dataIndex === 'actions'">
                    <a-popconfirm v-if="listArea.length"
                        :title="'¿Está seguro que quiere eliminar el área ' + record.name"
                        @confirm="onDelete(record.id, record.name)">
                        <a-radio-button value="large">
                            <delete-outlined />
                        </a-radio-button>
                    </a-popconfirm>
                    <!-- <BrandFormVue @update-list="fetchBrands" :record="record" /> -->
                </template>
            </template>
        </a-table>

    </div>
</template>
<script setup>

import { ref, onMounted, computed } from 'vue';
import { DeleteOutlined } from '@ant-design/icons-vue';
import { getAllAreas, removeArea } from '../composables/Area';
import { successNotification, errorNotification } from '../composables/Notification';

const columns = [
    {
        title: 'ID',
        dataIndex: 'id',
    },
    {
        title: 'Nombre',
        dataIndex: 'name',
    },
    {
        title: 'Descripción',
        dataIndex: 'description',
    },
    {
        title: 'Acciones',
        dataIndex: 'actions'
    }
];

const listArea = ref([]);
const countArea = computed(() => listArea.value.length);

const onDelete = async (areaId, name) => {
    try {
        const result = await removeArea(areaId);
        successNotification("Eliminación exitosa!", `El área ${name} fue eliminada.`);
        await fetchAreas();
    }
    catch (e) {
        if (e.response) {
            const data = await e.response.json();
            if (data.errors) {
                errorNotification("Ocurrió un error :(", data.errors[0].defaultMessage);
                return;
            }
            errorNotification("Ocurrió un error al eliminar el área :(", data.message);
        }

    }
};

const fetchAreas = async () => {
    const result = await getAllAreas();
    const data = await result.json();
    listArea.value = data;
};

onMounted(async () => {
    await fetchAreas();
});

</script>

