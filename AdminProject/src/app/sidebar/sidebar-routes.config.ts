import { MenuType, RouteInfo } from './sidebar.metadata';
export const ROUTES: RouteInfo[] = [
    { path: './account', title: 'Account', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './order', title: 'Order', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './notify', title: 'Notify', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './user', title: 'User', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './blog', title: 'Blog', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './event', title: 'Event', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './category', title: 'Categories', menuType: MenuType.LEFT, icon: 'material-icons' },   
    { path: './product', title: 'Product', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './pricebyday', title: 'Price by day', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './meaning', title: 'Flower meaning', menuType: MenuType.LEFT, icon: 'material-icons' },
    { path: './import', title: 'Import Product', menuType: MenuType.LEFT, icon: 'material-icons' }
];