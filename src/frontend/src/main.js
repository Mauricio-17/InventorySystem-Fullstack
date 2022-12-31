import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

//import './assets/main.css'; getting rid 
//import '@coreui/coreui/dist/css/coreui.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'ant-design-vue/dist/antd.css';

import { Button, MenuItem, SubMenu, Menu, Layout, LayoutSider, LayoutHeader, LayoutContent, Breadcrumb, BreadcrumbItem, LayoutFooter, Drawer, Form, FormItem, Switch, CheckboxGroup, Checkbox, Radio, RadioGroup, Input, InputPassword, Select, SelectOption, Popconfirm, Table, RadioButton, Badge, Tag, Pagination, Textarea, InputNumber } from 'ant-design-vue';

const app = createApp(App);

app.use(router);
app.use(Button);
app.use(Menu);
app.use(MenuItem);
app.use(SubMenu);

app.use(Layout);
app.use(LayoutContent);
app.use(LayoutSider);
app.use(LayoutHeader);
app.use(Breadcrumb);
app.use(BreadcrumbItem);
app.use(LayoutFooter);

app.use(Drawer);
app.use(Form);
app.use(FormItem);
app.use(Switch);
app.use(CheckboxGroup);
app.use(Checkbox);
app.use(Radio);
app.use(RadioGroup);
app.use(Input);
app.use(InputPassword);
app.use(Select);
app.use(SelectOption);

app.use(Table);
app.use(Popconfirm);
app.use(RadioButton);
app.use(Badge);
app.use(Tag);
app.use(Pagination);
app.use(Textarea);
app.use(InputNumber);

app.mount('#app');
