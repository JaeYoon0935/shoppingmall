export function getDeliveryDate(offset = 3) {
    const date = new Date();
    date.setDate(date.getDate() + offset);
    const week = ['일', '월', '화', '수', '목', '금', '토'];
    return `${date.getMonth() + 1}/${date.getDate()}(${week[date.getDay()]})`;
}