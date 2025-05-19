export function getDeliveryDate(offset = 3) {
    const date = new Date();
    date.setDate(date.getDate() + offset);
    const week = ['일', '월', '화', '수', '목', '금', '토'];
    return `${date.getMonth() + 1}/${date.getDate()}(${week[date.getDay()]})`;
}

export function formatPhoneNumber(phone) {
  const numberStr = phone.replace(/\D/g, '');

  if (numberStr.length === 11) {
    return numberStr.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
  }
  if (numberStr.length === 10) {
    return numberStr.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
  }
  return phone;
}